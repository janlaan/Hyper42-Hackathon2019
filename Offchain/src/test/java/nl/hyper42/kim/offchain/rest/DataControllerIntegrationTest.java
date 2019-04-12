package nl.hyper42.kim.offchain.rest;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Optional;
import nl.hyper42.kim.offchain.model.generated.api.TravelData;
import nl.hyper42.kim.offchain.service.DataService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class DataControllerIntegrationTest {

    private static final String URL_DATA = "/data";
    private static final String URL_DATA_GET = URL_DATA + "/%s";
    private static final String URL_DATA_POST = URL_DATA + "/";

    @Mock
    DataService dataService;

    @Autowired
    @InjectMocks
    DataController dataController = new DataController();

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void gatherDataSuccessTest() throws Exception {
        Mockito.when(dataService.gatherDataById(DataControllerTestData.ID)).thenReturn(Optional.of(DataControllerTestData.createTravelData()));
        this.mockMvc.perform(get(String.format(URL_DATA_GET, DataControllerTestData.ID))).andExpect(status().isOk())
                .andExpect(content().string(containsString(DataControllerTestData.DATA_HASH)));
        Mockito.validateMockitoUsage();
    }

    @Test
    public void gatherDataNotFoundTest() throws Exception {
        Mockito.when(dataService.gatherDataById(DataControllerTestData.ID)).thenReturn(Optional.empty());
        this.mockMvc.perform(get(String.format(URL_DATA_GET, DataControllerTestData.ID))).andExpect(status().isNoContent());
        Mockito.validateMockitoUsage();
    }

    @Test
    public void submitDataSuccessTest() throws Exception {
        TravelData travelData = DataControllerTestData.createTravelData();
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(travelData);
        System.out.println(json);
        Mockito.when(dataService.submitData(travelData)).thenReturn(DataControllerTestData.ID);
        this.mockMvc.perform(post(URL_DATA_POST).contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isCreated())
                .andExpect(content().string(containsString(DataControllerTestData.ID)));
        Mockito.validateMockitoUsage();
    }

}
