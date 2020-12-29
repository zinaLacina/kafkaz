package com.zina.kafkaz.web;

import com.zina.kafkaz.exception.TopicNotFoundException;
import com.zina.kafkaz.model.NmConsumer;
import com.zina.kafkaz.model.NmTopic;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

/**
 * @author : lacinazina
 * @created : 12/28/20, Monday
 **/
@RestController
public class TopicController {
//    @ApiOperation(value = "getTopic", notes = "Get details for a topic")
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "Success", response = NmTopic.class),
//            @ApiResponse(code = 404, message = "Invalid topic name")
//    })
//    @RequestMapping(path = "/{name:.+}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
//    public @ResponseBody
//    NmTopic getTopic(@PathVariable("name") String topicName) {
//        return kafkaMonitor.getTopic(topicName)
//                .orElseThrow(() -> new TopicNotFoundException(topicName));
//    }
//
//    @ApiOperation(value = "getAllTopics", notes = "Get list of all topics")
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "Success", response = String.class, responseContainer = "List")
//    })
//    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
//    public @ResponseBody
//    List<NmTopic> getAllTopics() {
//        return kafkaMonitor.getTopics();
//    }
//
//    @ApiOperation(value = "getConsumers", notes = "Get consumers for a topic")
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "Success", response = String.class, responseContainer = "List"),
//            @ApiResponse(code = 404, message = "Invalid topic name")
//    })
//    @RequestMapping(path = "/{name:.+}/consumers", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
//    public @ResponseBody List<NmConsumer> getConsumers(@PathVariable("name") String topicName) {
//        final var topic = kafkaMonitor.getTopic(topicName)
//                .orElseThrow(() -> new TopicNotFoundException(topicName));
//        return kafkaMonitor.getConsumers(Collections.singleton(topic));
//    }
}
