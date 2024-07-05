package com.curso_loiane.crud_spring.dto;

import java.util.List;

public record CoursePageDTO(List<CourseDTO> courses, int totalPages, long totalElements) {
}
