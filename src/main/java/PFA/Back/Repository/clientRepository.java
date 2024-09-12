package PFA.Back.Repository;
import PFA.Back.Model.client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface clientRepository extends JpaRepository <client,Long> {

}
