/**
 * 
 */
package com.security.server;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Aman
 *
 */
@RestController
@RequestMapping("/test/security")
public class HelloController {

	@GetMapping(value = "/hello/{name}")
	public String testHello(@PathVariable String name) {

		return "Hello " + name + " !";
	}

}
