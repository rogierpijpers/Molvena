# Molveno
[![Build Status](https://travis-ci.org/rogierpijpers/Molvena.png)](https://travis-ci.org/rogierpijpers/Molvena)

## Testing manifesto

Follow these guidelines for creating tests from this branch.

### Repository testing (integration)
These tests are ran against a testing instance of a H2 database. These tests belong in the package ```com.capgemini.data```.
Annotate your class with the following:
```
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { MolvenolakeresortApplication.class, TestJpaConfig.class }, loader = AnnotationConfigContextLoader.class)
@Transactional
```

In these tests you are allowed to autowire a repository, like this:
```
@Autowired
ReservationRepository reservationRepository;
```

Please seed the database before testing with ```@Before``` or ```@BeforeClass``` and clean up your mess after with ```@After``` or ```@AfterClass```.

### Domain testing
These tests are meant for the functionality of your domain model. Note that autowiring ANYTHING is not allowed in here!
expected package is ```com.capgemini.domain```. This means saving or retrieving data from the database is also not permitted.

### Service testing
In this package (```com.capgemini.service```) the functionality of services are tested.
Annotate the class with:
```
@RunWith(MockitoJUnitRunner.class)
```

In this test you are expected to mock a repositoy and inject this mock into the service to be tested. Like so:
```
@Mock
HypotheticalRepository hypotheticalRepository;

@InjectMocks
HypotheticalService hypotheticalService;
```

### Controller testing
These tests are meant for the controller layer. These tests are expected in the package ```com.capgemini.web```.
Annotate these classes like so:
```
@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
```

Mock the repositories for these tests, so functionality from controller to service are tested.