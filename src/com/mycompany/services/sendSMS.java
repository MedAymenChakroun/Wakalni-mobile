/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

/**
 *
 * @author dell
 */
public class sendSMS {
 
    

    public static void send() {
        Twilio.init("AH8q3dGK2f2vLZVgbRfLTjQPySe2yRaJHs", "dc0f6d05e01a50abcecff12b7c06d7a9");
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber("+21656564456"),
                new com.twilio.type.PhoneNumber("+19786794809"),
                "Votre commande est arrivé")
            .create();

        System.out.println(message.getSid());
    }
    
}
