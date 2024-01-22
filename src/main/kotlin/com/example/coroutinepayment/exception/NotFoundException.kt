package com.example.coroutinepayment.exception

import org.springframework.http.HttpStatus.NOT_FOUND
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(NOT_FOUND)
class NoProductException(message: String): Throwable(message)

@ResponseStatus(NOT_FOUND)
class NoOrderException(message: String = "상품 없음"): Throwable(message)