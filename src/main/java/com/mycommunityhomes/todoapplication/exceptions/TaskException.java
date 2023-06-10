package com.mycommunityhomes.todoapplication.exceptions;

import lombok.*;
import org.springframework.http.HttpStatus;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskException extends RuntimeException {
	
	private String message;
	private HttpStatus httpStatus;
}
