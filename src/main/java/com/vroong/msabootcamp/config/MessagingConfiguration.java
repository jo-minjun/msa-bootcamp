package com.vroong.msabootcamp.config;

import com.vroong.msabootcamp.stream.ConsumerChannel;
import com.vroong.msabootcamp.stream.ProducerChannel;
import org.springframework.cloud.stream.annotation.EnableBinding;

@EnableBinding(value = {ProducerChannel.class, ConsumerChannel.class})
public class MessagingConfiguration {
}