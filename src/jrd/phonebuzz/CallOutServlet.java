package jrd.phonebuzz;

import java.io.IOException;
import java.util.Map;
import java.util.HashMap;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.instance.Account;
import com.twilio.sdk.resource.instance.Call;
import com.twilio.sdk.resource.factory.CallFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.twilio.sdk.resource.instance.Account;

/**
 * Servlet implementation class CallOutServlet
 */
public class CallOutServlet extends HttpServlet {

  public static final String ACCOUNT_SID = "AC2da10a450be1f811b14e55d4f1ff77b3";
  public static final String AUTH_TOKEN = "af0ef05a2a7a48c4b01118aca57b3671";
  
  public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
    
    TwilioRestClient client = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN);
    Account mainAccount = client.getAccount();
    CallFactory callFactory = mainAccount.getCallFactory();
    Map<String, String> callParams = new HashMap<String, String>();
    callParams.put("To", request.getParameter("number")); // Replace with your phone number
    callParams.put("From", "6162025118"); // Replace with a Twilio number
    
    String url = request.getRequestURL().toString();
    url = url.substring(0, url.indexOf(request.getServletPath()));
    callParams.put("Url", url + "/greeting");
    // Make the call
    Call call;
    try {
      call = callFactory.create(callParams);
    } catch (TwilioRestException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}
