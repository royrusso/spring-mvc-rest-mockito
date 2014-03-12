package org.royrusso.mvc.controller;

/*
 * (C) Copyright 2014 Roy Russo
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

import org.royrusso.mvc.domain.User;
import org.royrusso.mvc.rest.RestResponse;
import org.royrusso.mvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public RestResponse<User> getUser(@PathVariable("id") int id) {
        User user = userService.getUser(id);
        return new RestResponse<User>(true, "", user);
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public RestResponse saveUser(@RequestBody User user) {
        userService.saveUser(user);
        return new RestResponse<String>(true, "", null);
    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public RestResponse deleteUser(@PathVariable("id") int id) {
        userService.deleteUser(id);
        return new RestResponse<String>(true, "", null);
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.PUT, produces = "application/json")
    public RestResponse updateUser(@RequestBody User user) {
        userService.updateUser(user);
        return new RestResponse<String>(true, "", null);
    }
}
