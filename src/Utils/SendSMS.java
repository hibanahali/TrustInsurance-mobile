/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;


import com.nexmo.client.NexmoClient;
import com.nexmo.client.NexmoClientException;
import com.nexmo.client.auth.AuthMethod;
import com.nexmo.client.auth.TokenAuthMethod;
import com.nexmo.client.sms.SmsSubmissionResult;
import com.nexmo.client.sms.messages.TextMessage;
import java.io.IOException;
/**
 *
 * @author elbrh
 */
public class SendSMS {
    public static void sendSms() {
        AuthMethod auth = new TokenAuthMethod("9d689281", "Fb0sgYYHVuaqDb1Y");
        NexmoClient client = new NexmoClient(auth);
        TextMessage message = new TextMessage("Trust Assurance", "21628286015", "You Get a new comment go check it.");
        try {
            SmsSubmissionResult[] responses = client.getSmsClient().submitMessage(message);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        } catch (NexmoClientException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
