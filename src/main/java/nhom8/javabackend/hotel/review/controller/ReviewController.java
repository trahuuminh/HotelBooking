package nhom8.javabackend.hotel.review.controller;

import java.util.Optional;

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

@RestController
@RequestMapping("/api/review")
public class ReviewController {
	
	private ReviewService service;
	
	public ReviewController(ReviewService reviewService) {
		service=reviewService;
	}
	
	@GetMapping("/find-all-review")
	public Object findAllReview(@RequestParam("p") Optional<Integer>p) {
		Pageable pageable=PageRequest.of(p.orElse(0), 5,Sort.by("id"));
		Page<ReviewDto> reviews=service.findAllReviewDto(pageable);
		
		return ResponseHandler.getResponse(service.pagingFormat(reviews),HttpStatus.OK);
	}
	
	@PostMapping("/create-new-review")
	public Object CreateReview(@Valid @RequestBody CreateReviewDto dto, BindingResult errors) {
		if(errors.hasErrors())
			return ResponseHandler.getResponse(errors,HttpStatus.BAD_REQUEST);
		
		Review review=service.createNewReview(dto);
		
		return ResponseHandler.getResponse(review,HttpStatus.CREATED);
	}
	
	@PutMapping("/update-review")
	public Object updateReview(@Valid @RequestBody UpdateReviewDto dto, BindingResult errors) {
		if(errors.hasErrors())
			return ResponseHandler.getResponse(errors,HttpStatus.BAD_REQUEST);
		
		Review review=service.updateReview(dto);
		
		return ResponseHandler.getResponse(review,HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{review-id}")
	public Object deleteReview(@PathVariable("review-id") Long id) {
		if(!service.isExistedId(id))
			return ResponseHandler.getResponse("Review doesn't exist",HttpStatus.BAD_REQUEST);
		
		service.deleteReview(id);
		
		return ResponseHandler.getResponse(HttpStatus.OK);
	}
}
