package com.example.demo;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import com.example.demo.controllers.ChannelController;
import com.example.demo.entities.Channel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

    @Component
   public class ChannelModelAssembler implements RepresentationModelAssembler<Channel, EntityModel<Channel>> {

        @Override
        public EntityModel<Channel> toModel(Channel channel) {

            return EntityModel.of(channel, //
                    linkTo(methodOn(ChannelController.class).getChannel(channel.getId())).withSelfRel(),
                    linkTo(methodOn(ChannelController.class).showAll()).withRel("channels"));
        }
    }

