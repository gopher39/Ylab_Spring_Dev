package com.edu.ulab.app.validation;

import com.edu.ulab.app.exception.BadRequestException;
import com.edu.ulab.app.exception.WrongIdException;
import com.edu.ulab.app.web.request.BookRequest;
import com.edu.ulab.app.web.request.UserRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ValidatorService {
    public void validateCreateUserRequest(UserRequest request) {
        if (request == null) {
            throw new BadRequestException("User is null, it is wrong:)");
        }
        if (request.getId() != null) {
            throw new BadRequestException("User id field is not empty, user id will be generated on creation");
        }
    }
    public void validateCreateBookRequest(List<BookRequest> bookRequests) {
        if(bookRequests.stream().anyMatch(Objects::isNull)){
            throw new BadRequestException("The book is null");
        }
    }

    public void validateUpdateUserRequest(UserRequest request) {
        if (request == null) {
            throw new BadRequestException("User is nul, it is wrong:)");
        }
        if (request.getId() == null) {
            throw new BadRequestException("To update a user, please enter his id.");
        }
        if (request.getId() < 0) {
            throw new WrongIdException("The number of the id should be positive.");
        }
    }

    public void validateUpdateBookRequest(List<BookRequest> bookRequests){
        if(bookRequests.stream().anyMatch(Objects::isNull)){
            throw new BadRequestException("Book is null, it should not be:)");
        }
    }

    public void validateId(Long id) {
        if (id == null || id < 0 ) {
            throw new WrongIdException("Check id");
        }
    }
}
