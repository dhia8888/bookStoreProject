/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.CustomerBalanceTransaction;

import java.util.HashMap;
import java.util.Map;

import com.stripe.model.PaymentMethod;

/**
 * @author DELL
 */
public class Paiement {


    public static String ajouterCustomer(String email) {

        Stripe.apiKey = "sk_test_51HwU4iGeZG2hevztMHMDDn73ifZrysAi3WukxJCMV4BHkzD3Vl30rHJlKczuPsA6hKOkXFmErg8d3EXje23686xv003R2g9WFJ";

        Map<String, Object> customerMap = new HashMap<String, Object>();
        customerMap.put("description", "");
        customerMap.put("email", email);


        try {
            Customer customer = Customer.create(customerMap);
            System.out.println(customer.getId());
            return customer.getId();
        } catch (StripeException e) {
            e.printStackTrace();

            return "";
        }
    }

    public static String ajouterMethodePaiement(String num_carte, String mois, String annee, String cvc) throws StripeException {

        Map<String, Object> card = new HashMap<>();
        card.put("number", num_carte);
        card.put("exp_month", mois);
        card.put("exp_year", annee);
        card.put("cvc", cvc);
        Map<String, Object> params = new HashMap<>();
        params.put("type", "card");
        params.put("card", card);

        PaymentMethod paymentMethod =
                PaymentMethod.create(params);

        return paymentMethod.getId();
    }

    public static void attacherCostumerPourMethodePaiement(String customer_id, String id_paiement_method) throws StripeException {
        PaymentMethod paymentMethod =
                PaymentMethod.retrieve(
                        id_paiement_method
                );

        Map<String, Object> params = new HashMap<>();
        params.put("customer", customer_id);

        PaymentMethod updatedPaymentMethod =
                paymentMethod.attach(params);


    }

    public static void effectuerPaiement(String somme, String devise, String customer_id) throws StripeException {

        Customer customer =
                Customer.retrieve(customer_id);

        Map<String, Object> params = new HashMap<>();
        params.put("amount", -Integer.parseInt(somme));
        params.put("currency", devise);

        CustomerBalanceTransaction balanceTransaction =
                customer.balanceTransactions().create(params);

    }

}
    

