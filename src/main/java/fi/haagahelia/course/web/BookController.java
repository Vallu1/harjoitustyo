package fi.haagahelia.course.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ch.qos.logback.classic.net.SyslogAppender;
import fi.haagahelia.course.domain.Book;
import fi.haagahelia.course.domain.BookRepository;
import fi.haagahelia.course.domain.CategoryRepository;

@Controller
public class BookController {

	@Autowired
	private BookRepository repository; 
	
	@Autowired
	private CategoryRepository drepository; 
	
    @RequestMapping(value="/booklist")
    public String bookList(Model model) {	
        model.addAttribute("books", repository.findAll());
        return "booklist";
    }
  
    // vko3, t3: Create feature, osa1
    // Avaa addbook.html sivun ja tarjoaa sille tyhjän book-olion
    @RequestMapping(value = "/add")
    public String addBook(Model model){
    	model.addAttribute("book", new Book());
    	model.addAttribute("category", drepository.findAll());
    	//mennään addbook.html sivulle
        return "addbook";
    }     
    
    // vko3, t3: Create feature, osa2 tekee varsinaisen tallennuksen
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Book book){
    	// käytetään Crudrepository-luokan valmista metodia
    	System.out.println("BookController.save()");
        repository.save(book);
        
        return "redirect:booklist";
    }    

    // vko3, t3: Delete ominaisuus
    // luetaan polkumuuttujasta deletoitavan kirjan id
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteBook(@PathVariable("id") Long Id, Model model) {
       	// käytetään Crudrepository-luokan valmista metodia
    	// System.out.println("BookController.deleteBook()");
    	System.out.println("täällä mennään ");
    	repository.deleteById(Id);
    	
        return "redirect:../booklist";
    } 
    
    // vko3, t4: Luetaan muutettavan olion id @PathVariablen avulla 
    // etsitään id:n perusteella entiteetti huom. loppuun .get jotta saat vast
    // ja tallennetaan se book olioon
    
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String updateBook(@PathVariable("id") Long Id, Model model) {
    	Book book = repository.findById(Id).get();
    	System.out.println("update book " + book.toString());
    	model.addAttribute("book", book);
    	model.addAttribute("category", drepository.findAll());
    	System.out.println("täällä mennään uopdatessa");
        return "updatebook";
    }
}