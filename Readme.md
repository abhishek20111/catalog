# Shamir's Secret Sharing - Polynomial Coefficient Calculator

## Overview

This project implements a simplified version of Shamir's Secret Sharing algorithm in Java to find the constant term \( c \) of a polynomial given its roots in JSON format. The polynomial is represented as:

\[
f(x) = a_m x^m + a_{m-1} x^{m-1} + \ldots + a_1 x + c
\]

The goal is to decode the provided roots, calculate the coefficients using polynomial interpolation, and derive the constant term \( c \).

## Requirements

- Java Development Kit (JDK) 8 or higher
- JSON library for Java (e.g., `org.json`)

## Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/shamir-secret-sharing.git
   cd shamir-secret-sharing


## Usage
Prepare your input JSON file with the roots.
      Compile and run the main Java program:
      bash

javac Main.java
java Main input.json
Example Input
Test Case 1:

## json

\[{
    "keys": {
        "n": 4,
        "k": 3
    },
    "1": {
        "base": "10",
        "value": "4"
    },
    "2": {
        "base": "2",
        "value": "111"
    },
    "3": {
        "base": "10",
        "value": "12"
    },
    "6": {
        "base": "4",
        "value": "213"
    }
} \]
Test Case 2:

## json

``` {
  "keys": {
    "n": 10,
    "k": 7
  },
  "1": {
    "base": "6",
    "value": "13444211440455345511"
  },
  "2": {
    "base": "15",
    "value": "aed7015a346d63"
  },
  "3": {
    "base": "15",
    "value": "6aeeb69631c227c"
  },
  "4": {
    "base": "16",
    "value": "e1b5e05623d881f"
  },
  "5": {
    "base": "8",
    "value": "316034514573652620673"
  },
  "6": {
    "base": "3",
    "value": "2122212201122002221120200210011020220200"
  },
  "7": {
    "base": "3",
    "value": "20120221122211000100210021102001201112121"
  },
  "8": {
    "base": "6",
    "value": "20220554335330240002224253"
  },
  "9": {
    "base": "12",
    "value": "45153788322a1255483"
  },
  "10": {
    "base": "7",
    "value": "1101613130313526312514143"
  }
} 
Output
Output for Test Case 1:



The constant term c of the polynomial is approximately 25.8.

Output for Test Case 2:



The constant term c of the polynomial is approximately [Final Value].

