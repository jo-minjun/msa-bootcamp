package com.vroong.msabootcamp.config;

import com.vroong.msabootcamp.stream.ProducerChannel;
import org.springframework.cloud.stream.annotation.EnableBinding;

@EnableBinding(value = {ProducerChannel.class})
public class MessagingConfiguration {
}