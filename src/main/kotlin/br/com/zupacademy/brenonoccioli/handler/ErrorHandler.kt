package br.com.zupacademy.brenonoccioli.handler

import io.micronaut.aop.Around
import kotlin.annotation.AnnotationRetention.*
import kotlin.annotation.AnnotationTarget.*

@MustBeDocumented
@Retention(RUNTIME)
@Target(CLASS, FIELD, TYPE)
@Around
annotation class ErrorHandler{}
