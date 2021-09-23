package br.com.zupacademy.brenonoccioli

import io.micronaut.runtime.Micronaut.*
fun main(args: Array<String>) {
	build()
	    .args(*args)
		.packages("br.com.zupacademy.brenonoccioli")
		.start()
}

