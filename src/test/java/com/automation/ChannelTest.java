package com.automation;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.io.IOException;

import static com.automation.Constants.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;

/**
 * Created by shirisha
 * since  3/6/20.
 */
public class ChannelTest {

    public HelperClass helperClass;
    private String name;
    private String channel_id;
    private String channel_name;
    private String rename;
    private String rename_channel_id;
    private String rename_channel_name;


    @BeforeTest
    public void setUp() throws IOException, InterruptedException{

        helperClass = new HelperClass();
        name=helperClass.getRandomId();
        rename=helperClass.getRandomId();

    }


    @Test(priority = 0)
    public void createChannel(){
        Response response= given().
                contentType(ContentType.JSON).
                queryParam("token","xoxp-973718004483-984730463748-985211934965-e56556b01f6cf9065f70a7d22b3d817e").
                queryParam("name",name).
                queryParam("pretty","1").
                queryParam("validate",name).
                log().all().
                expect().
                log().all().
                statusCode(200).
                body("ok", is("true")).
                when().
                post(CREATE_CHANNELS);
        channel_id=response.path("ok.channel.id");
        channel_name=response.path("ok.channel.name");

    }

    @Test(priority = 1)
    public void joinChannel(){
       Response response= given().
                contentType(ContentType.JSON).
                queryParam("token","xoxp-973718004483-984730463748-985211934965-e56556b01f6cf9065f70a7d22b3d817e").
                queryParam("name",channel_name).
                queryParam("pretty","1").
               queryParam("validate",channel_name).
                log().all().
                expect().
                log().all().
                statusCode(200).
                body("ok", is("true")).
                when().
                post(JOIN_CHANNEL);



    }

       @Test(priority = 2)
    public void renameChannel(){
           Response response=given().
                contentType(ContentType.JSON).
                queryParam("token","xoxp-973718004483-984730463748-985211934965-e56556b01f6cf9065f70a7d22b3d817e").
                queryParam("channel",channel_id).
                queryParam("name",rename).
                queryParam("pretty","1").
                queryParam("validate",rename).
                log().all().
                expect().
                log().all().
                statusCode(200).
                when().
                post(RENAME_CHANNEL);
           rename_channel_id=response.path("ok.channel.id");
           rename_channel_name=response.path("ok.channel.name");

    }

    @Test(priority = 3)
    public void listhannel(){
       Response response=  given().
                contentType(ContentType.JSON).
                queryParam("token","xoxp-973718004483-984730463748-985211934965-e56556b01f6cf9065f70a7d22b3d817e").
                queryParam("name","test1").
                queryParam("pretty","1").
                log().all().
                expect().
                log().all().
                statusCode(200).
                when().
                get(LIST_ALL_CHANNELS);

        JSONArray array = new JSONArray(response.jsonPath().getList("channels"));

        int size = array.length();
        System.out.println("Size: " + size);

        for (int i = 0; i < array.length(); i++) {
            JSONObject channnelDetails = array.getJSONObject(i);

            if (channnelDetails.get("id").toString().toLowerCase().contains(rename_channel_id)){

                System.out.println("Channel Name: " + channnelDetails.get("name"));

                Assert.assertEquals(channnelDetails.get("name"), rename_channel_name);
            }
        }
    }


    @Test(priority = 4)
    public void archieveChannel(){
        given().
                contentType(ContentType.JSON).
                queryParam("token","xoxp-973718004483-984730463748-985211934965-e56556b01f6cf9065f70a7d22b3d817e").
                queryParam("channel",rename_channel_id).
                queryParam("pretty","1").
                log().all().
                expect().
                log().all().
                statusCode(200).
                body("ok", is("true")).
                when().
                post(ARCHIEVE_CHANNEL);
    }

    @Test(priority = 5)
    public void infoChannel(){
       Response response= given().
                contentType(ContentType.JSON).
                queryParam("token","xoxp-973718004483-984730463748-985211934965-e56556b01f6cf9065f70a7d22b3d817e").
                queryParam("channel",rename_channel_id).
                queryParam("pretty","1").
                log().all().
                expect().
                log().all().
                statusCode(200).
                when().
                get(CHANNEL_INFO);
        Assert.assertEquals(response.path("ok.channel.is_archived"),true);

    }

}
