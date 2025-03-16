package com.github.molt.authorizationserver.producers

import com.github.molt.authorizationserver.domain.ResetPassword
import com.github.molt.authorizationserver.dto.EmailDto
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class ResetPasswordProducer(

    private val rabbitTemplate: RabbitTemplate,

    @Value("\${broker.queue.email.name}")
    private val routingKey: String

) {

    fun publishEmail(resetPassword: ResetPassword, email: String, fullName: String) {

        rabbitTemplate.convertAndSend(
            "",
            routingKey,
            EmailDto(
                email,
                subject = "Reset password",
                template = "RESET_PASSWORD_TEMPLATE",
                variables = mapOf(
                    "full_name" to fullName,
                    "url" to "http://localhost:8080/auth/reset?token=${resetPassword.token}"
                )
            )
        )

    }

}