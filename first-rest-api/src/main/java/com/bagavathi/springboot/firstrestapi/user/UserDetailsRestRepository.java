package com.bagavathi.springboot.firstrestapi.user;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserDetailsRestRepository extends PagingAndSortingRepository<UserDetails,Long> {

}