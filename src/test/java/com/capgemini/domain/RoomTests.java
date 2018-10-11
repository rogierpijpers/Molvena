package com.capgemini.domain;

import com.capgemini.TestJpaConfig;
import com.capgemini.web.MolvenolakeresortApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { MolvenolakeresortApplication.class, TestJpaConfig.class }, loader = AnnotationConfigContextLoader.class)
@Transactional
public class RoomTests {
    // TODO: do....
    @Before
    public void initRoomData(){

    }

    @Test
    public void testAddRoomType(){

    }

    @Test
    public void testGetRoomType(){

    }

    @Test
    public void testAddRoomTypes(){
        
    }
}
