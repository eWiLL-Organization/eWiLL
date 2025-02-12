package com.wipdev.eWiLL_backend.utils.fbs

import com.auth0.jwt.JWT
import com.wipdev.eWiLL_backend.security.auth.JwtUtils.Companion.FBSTokenDecodingResult
import io.swagger.v3.core.util.Json
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpHeaders
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.nio.charset.StandardCharsets
import javax.servlet.http.HttpServletRequest
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service

@Component
class FbsClient{
    @Value("\${app.fbs.url}")
    private var baseUrl: String = "https://feedback.mni.thm.de/api/v1"

    fun getLoginLdap(username: String, password: String, servletRequest: HttpServletRequest): HttpResponse<String>? {
        val url = "$baseUrl/login/ldap"
        val client = HttpClient.newBuilder().build()

        val passwordBytes = password.toByteArray(StandardCharsets.UTF_8)
        val requestBody =
            "{\"username\":\"$username\",\"password\":\"${String(passwordBytes, StandardCharsets.UTF_8)}\"}"



        val request = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .header("Content-Type", "application/json")
            .header("X-Forwarded-For", servletRequest.remoteAddr)
            .POST(HttpRequest.BodyPublishers.ofString(requestBody))
            .build()
        return client.send(request, HttpResponse.BodyHandlers.ofString())


    }

    fun getUserInformation(authHeader: String?, xForwardedForHeader : String?): FbsUser {
        val decodingResult = decodeFBSToken(
            authHeader?.subSequence("Bearer ".length, authHeader.length).toString()
        )
        val id = decodingResult.userID
        val url = "$baseUrl/users/${id}"
        val client = HttpClient.newBuilder().build()

        val request =
            HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Authorization", authHeader)
                .header("X-Forwarded-For", xForwardedForHeader)
                .GET()
                .build()

        val response = client.send(request, HttpResponse.BodyHandlers.ofString())
        return if (response.statusCode() != 200) {
            FbsUser()
        } else {
            Json.mapper().readValue(response.body(), FbsUser::class.java)
        }

    }


    private fun decodeFBSToken(token: String?): FBSTokenDecodingResult {
        val decodedJWT = JWT.decode(token)
        return FBSTokenDecodingResult(
            decodedJWT.getClaim("id").asInt(),
            decodedJWT.getClaim("username").asString(),
            "",
            ""
        )
    }

    class FbsUser {
        var id: Int? = 0
        var prename: String? = ""
        var surname: String? = ""
        var email: String? = ""
        var username: String? = ""
        var password: String? = ""
        var alias: String? = ""
        var globalRole: String? = ""
        var name: String? = ""

        override fun toString(): String {
            return "FbsUser(id=$id, prename='$prename', surname='$surname', email='$email', username='$username', alias='$alias', globalRole='$globalRole', name='$name')"
        }
    }
}