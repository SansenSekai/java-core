package com.java.learning.inheritance.payment;

/**
 * Детали платежа — sealed interface с разными реализациями
 * для разных способов оплаты.
 */
public sealed interface PaymentDetails 
    permits CreditCardDetails, PayPalDetails, CryptoDetails {
}

