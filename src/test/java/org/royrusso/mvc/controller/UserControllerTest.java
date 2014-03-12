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


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.royrusso.mvc.domain.User;
import org.royrusso.mvc.service.impl.UserServiceImpl;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:test-context.xml", "classpath:mockTestBeans.xml"})
public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    private MockMvc mockMvc;

    @Spy
    private UserServiceImpl userService;

    @Before
    public void setup() {

        // this must be called for the @Mock annotations above to be processed
        // and for the mock service to be injected into the controller under
        // test.
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void testGet() throws Exception {

        int userId = 3;

        mockMvc.perform(
                get("/user/" + userId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("data.age", is(15)))
                .andExpect(jsonPath("data.firstName", is("bob")))
                .andExpect(jsonPath("data.id", is(userId)))
                .andExpect(jsonPath("success", is(true)));
    }

    @Test
    public void testSave() throws Exception {
        mockMvc.perform(
                post("/user")
                        .contentType(TestUtil.APPLICATION_JSON_UTF8)
                        .content(TestUtil.convertObjectToJsonBytes(new User()))
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("success", is(true)));
    }

    @Test
    public void testUpdate() throws Exception {
        mockMvc.perform(
                put("/user")
                        .contentType(TestUtil.APPLICATION_JSON_UTF8)
                        .content(TestUtil.convertObjectToJsonBytes(new User())))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("success", is(true)));
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(
                delete("/user/3"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("success", is(true)));
    }
}
