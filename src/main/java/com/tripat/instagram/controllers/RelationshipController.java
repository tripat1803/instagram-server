package com.tripat.instagram.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tripat.instagram.controllers.responses.RelationshipResponse;
import com.tripat.instagram.controllers.responses.UserResponse;
import com.tripat.instagram.models.User;
import com.tripat.instagram.models.UserRelationship;
import com.tripat.instagram.repositories.RelationRepository;
import com.tripat.instagram.repositories.UserRepository;
import com.tripat.instagram.services.NotificationService;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequestMapping("/api/relation")
public class RelationshipController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RelationRepository relationRepository;
    @Autowired
    NotificationService notificationService;

    @GetMapping("/create/{userId}")
    ResponseEntity<?> createRelation(@PathVariable("userId") String alias){
        Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();
        User relationWith = this.userRepository.findFirstByAlias(alias);
        User user = (User) authentication.getPrincipal();

        UserRelationship outgoing = new UserRelationship();
        outgoing.setOutgoingRequest(true);
        outgoing.setUser(user);
        outgoing.setRelationWith(relationWith);

        UserRelationship incoming = new UserRelationship();
        incoming.setIncomingRequest(true);
        incoming.setUser(relationWith);
        incoming.setRelationWith(user);

        this.relationRepository.save(outgoing);
        this.relationRepository.save(incoming);

        this.notificationService.createFollowRequestNotification(relationWith, user);

        return ResponseEntity.ok("Status Ok");
    }

    @GetMapping("/response/{userId}")
    ResponseEntity<?> acceptRelation(@PathVariable("userId") String alias, @RequestParam("type") String type){
        Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();
        User relationWith = this.userRepository.findFirstByAlias(alias);
        User user = (User) authentication.getPrincipal();

        UserRelationship outgoing = this.relationRepository.findFirstByUserAndRelationWith(relationWith, user);
        UserRelationship incoming = this.relationRepository.findFirstByUserAndRelationWith(user, relationWith);

        if(type == "decline"){
            this.relationRepository.delete(incoming);
            this.relationRepository.delete(outgoing);
            this.notificationService.deleteFollowRequestNotification(user, relationWith);
            return ResponseEntity.ok("Status Ok");
        }

        incoming.setIncomingRequest(false);
        incoming.setFollowedBy(true);

        outgoing.setOutgoingRequest(false);
        outgoing.setFollowing(true);

        this.relationRepository.save(incoming);
        this.relationRepository.save(outgoing);

        this.notificationService.modifyFollowAcceptNotification(user, relationWith);

        return ResponseEntity.ok("Status Ok");
    }

    @GetMapping("/{alias}")
    ResponseEntity<?> getRelation(@PathVariable("alias") String alias){
        Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();
        User relationWith = this.userRepository.findFirstByAlias(alias);
        User user = (User) authentication.getPrincipal();
        UserRelationship relation = this.relationRepository.findFirstByUserAndRelationWith(user, relationWith);
        RelationshipResponse relationRes = new RelationshipResponse(relation);
        UserResponse res = new UserResponse(relationWith, (relation != null) ? relationRes : null);
        return ResponseEntity.ok(res);
    }
}
