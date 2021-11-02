package nhom8.javabackend.hotel.review.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import nhom8.javabackend.hotel.common.responsehandler.ResponseHandler;
import nhom8.javabackend.hotel.review.dto.CreateReviewDto;
import nhom8.javabackend.hotel.review.dto.ReviewDto;
import nhom8.javabackend.hotel.review.dto.UpdateReviewDto;
import nhom8.javabackend.hotel.review.entity.Review;
import nhom8.javabackend.hotel.review.service.itf.ReviewService;
import nhom8.javabackend.hotel.security.jwt.JwtUtils;
import nhom8.javabackend.hotel.user.entity.User;
import nhom8.javabackend.hotel.user.service.itf.UserService;
import nhom8.javabackend.hotel.user.util.Role;

@RestController
@RequestMapping("/api/review")
public class ReviewController {
	
	private ReviewService service;
	private UserService userService;
	private JwtUtils jwt;
	
	public ReviewController(ReviewService reviewService,JwtUtils Jwt,UserService UserService) {
		service=reviewService;
		jwt=Jwt;
		userService=UserService;
	}
	
	@GetMapping("/find-all-review")
	public Object findAllReview(@RequestParam("p") Optional<Integer>p,HttpServletRequest request) {
		try {
			if(jwt.getJwtTokenFromRequest(request)==null)
				return ResponseHandler.getResponse("please sign in first",HttpStatus.BAD_REQUEST);
			else if(!userService.getUserByUsername(jwt.getUsernameFromToken(jwt.getJwtTokenFromRequest(request))).getRole().equals(Role.ADMIN))
				return ResponseHandler.getResponse("you're not allowed to search all reviews",HttpStatus.BAD_REQUEST);
			
			Pageable pageable=PageRequest.of(p.orElse(0), 22,Sort.by("id").descending());
			Page<ReviewDto> reviews=service.findAllReviewDto(pageable);
			
			return ResponseHandler.getResponse(service.pagingFormat(reviews),HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseHandler.getResponse("Unreachable token !",HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/create-new-review")
	public Object CreateReview(@Valid @RequestBody CreateReviewDto dto, BindingResult errors,HttpServletRequest request) {
		if(errors.hasErrors())
			return ResponseHandler.getResponse(errors,HttpStatus.BAD_REQUEST);
		else if(jwt.getJwtTokenFromRequest(request)==null)
			return ResponseHandler.getResponse("Please sign in first before leave a review ",HttpStatus.BAD_REQUEST);
			
		Review review=service.createNewReview(userService.getUserByUsername(jwt.getUsernameFromToken(jwt.getJwtTokenFromRequest(request))) ,dto);
		
		return ResponseHandler.getResponse(review,HttpStatus.CREATED);
	}
	
	@PutMapping("/update-review")
	public Object updateReview(@Valid @RequestBody UpdateReviewDto dto, BindingResult errors,HttpServletRequest request) {
		try {
			if(errors.hasErrors())
				return ResponseHandler.getResponse(errors,HttpStatus.BAD_REQUEST);
			else if(jwt.getJwtTokenFromRequest(request)==null)
				
				return ResponseHandler.getResponse("please sign in first",HttpStatus.BAD_REQUEST);
			
			User currentUser = userService.getUserByUsername(jwt.getUsernameFromToken(jwt.getJwtTokenFromRequest(request)));
			
			if(!currentUser.getRole().equals(Role.ADMIN) && currentUser != service.getReivewByReviewId(dto.getId()).getAuthor())
				return ResponseHandler.getResponse("you're not allowed to update this review",HttpStatus.BAD_REQUEST);
			
			Review review=service.updateReview(dto);
			
			return ResponseHandler.getResponse(review,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseHandler.getResponse("Unreachable token !",HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/delete/{review-id}")
	public Object deleteReview(@PathVariable("review-id") Long id,HttpServletRequest request) {
		try {
			if(!service.isExistedId(id))
				return ResponseHandler.getResponse("Review doesn't exist",HttpStatus.BAD_REQUEST);
			
			else if(jwt.getJwtTokenFromRequest(request)==null)
				return ResponseHandler.getResponse("please sign in first",HttpStatus.BAD_REQUEST);
			
			User currentUser = userService.getUserByUsername(jwt.getUsernameFromToken(jwt.getJwtTokenFromRequest(request)));
			
			if(!currentUser.getRole().equals(Role.ADMIN) && currentUser != service.getReivewByReviewId(id).getAuthor())
				return ResponseHandler.getResponse("you're not allowed to delete this review",HttpStatus.BAD_REQUEST);
			
			service.deleteReview(id);
			
			return ResponseHandler.getResponse(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseHandler.getResponse("Unreachable token !",HttpStatus.BAD_REQUEST);
		}
	}
}
