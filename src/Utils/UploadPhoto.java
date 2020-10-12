/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import com.codename1.components.InfiniteProgress;
import com.codename1.io.CharArrayReader;
import com.codename1.io.JSONParser;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author elbrh
 */
public class UploadPhoto {

    public UploadPhoto() {
    }
    

    public static String pictureUpload(String filePath) {
        ArrayList<String> image = new ArrayList<>();
        String url = "http://localhost/PIJFinal/web/uploads/upload2.php";
        MultipartRequest cr = new MultipartRequest();
        cr.setUrl(url);
        cr.setPost(true);
        String mime = "image/jpeg";
        try {
            cr.addData("file", filePath, mime);
             cr.setFilename("file", "MyImage.jpg");
        InfiniteProgress prog = new InfiniteProgress();
        Dialog dlg = prog.showInifiniteBlocking();
        cr.setDisposeOnCompletion(dlg);
        cr.addResponseListener(new ActionListener<NetworkEvent>() {
            
            @Override
            public void actionPerformed(NetworkEvent evt) {
               JSONParser jsonp = new JSONParser();

                try {
                    Map<String, Object> tasks = jsonp.parseJSON(new CharArrayReader(new String(cr.getResponseData()).toCharArray()));
                    System.out.println(tasks);
                    String statu = tasks.get("Status").toString();                    
                    if(statu.equals("ok")){
                     String img = tasks.get("image").toString();
                        image.add(img);
                   }
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }
           
        });
        NetworkManager.getInstance().addToQueueAndWait(cr);
        } catch (IOException exx) {
           System.out.println(exx.getMessage());
        }
       
        return image.get(0) ;
    }
}
