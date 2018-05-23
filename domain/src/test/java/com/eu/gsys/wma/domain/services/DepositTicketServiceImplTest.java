package java.com.eu.gsys.wma.domain.services;

import com.eu.gsys.wma.domain.services.DepositTicketServiceImpl;
import com.eu.gsys.wma.domain.transformers.DepositTicketTransformer;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DepositTicketServiceImplTest {

    @InjectMocks
    DepositTicketServiceImpl depositTicketServiceMock;

    @Mock
    DepositTicketTransformer depositTicketTransformerMock;

}