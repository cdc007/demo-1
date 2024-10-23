package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.HashSet;
import java.util.Set;

@RestController
public class NumberController {

    private final AtomicInteger currentNumber = new AtomicInteger(0);
    private final Set<Integer> sentNumbers = new HashSet<>();

    @GetMapping("/nextNumber")
    public synchronized Integer getNextNumber() {
        int number;
        do {
            number = currentNumber.getAndIncrement();
        } while (sentNumbers.contains(number) && number <= 200);

        if (number > 200) {
            return null; // All numbers have been sent
        }

        sentNumbers.add(number);
        return number;
    }
}