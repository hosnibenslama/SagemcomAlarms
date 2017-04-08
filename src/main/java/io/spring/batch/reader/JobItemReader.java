package io.spring.batch.reader;

import org.springframework.batch.item.ItemReader;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.List;

/**

 * @author hosni
 */

@Component
public class JobItemReader<T> implements ItemReader<T> {

    private  Iterator<T> data;


  /*  @Value("#{jobParameters['identifiant']}")
    private Long id;

    @Value("#{jobParameters['date']}")
    private Date date;*/

    public JobItemReader(){

    }

   /* public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public  Long getId() {
        return id;
    }

    public void setId(Long id) {
       this.id = id;
    }*/



    public JobItemReader(List<T> data) {
        this.data = data.iterator();
    }

    @Override
    public  T read() throws Exception {
        if(this.data.hasNext()) {
            return this.data.next();
        }
        return null;
    }


}
