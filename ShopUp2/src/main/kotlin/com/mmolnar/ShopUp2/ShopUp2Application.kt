package com.mmolnar.ShopUp2

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@EntityScan("com.mmolnar.ShopUp2.model")
@EnableJpaRepositories("com.mmolnar.ShopUp2.repository")
class ShopUp2Application

fun main(args: Array<String>) {
	runApplication<ShopUp2Application>(*args)
}
