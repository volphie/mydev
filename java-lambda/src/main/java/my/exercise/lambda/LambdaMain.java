package my.exercise.lambda;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.function.Predicate;

/**
 * Hello world!
 *
 */
public class LambdaMain 
{
   public static void main(String[] args) {
        List<Book> bookList = Arrays.asList(
                new Book("novel1", 10000, "novel"),
                new Book("novel2", 11000, "novel"),
                new Book("novel3", 12000, "novel"),

                new Book("history1", 10000, "history"),
                new Book("history2", 11000, "history"),
                new Book("history3", 12000, "history"),
				new Book("history4", 15000, "history"),

                new Book("computer1", 10000, "computer"),
                new Book("computer2", 11000, "computer"),
                new Book("computer3", 12000, "computer")
        );
		
		int price = 11000;
		List<Book> results = LambdaMain.collectBook(bookList, price);
		System.out.println(" 가격이 11000이 넘는 책의 수 : " + results.size());
		
		/* 익명 클래스에 의한 함수 파라미터 전달*/
		String bookName = "computer4";
		results = LambdaMain.collectBookByLambda(bookList, new BookPredicate(){
			@Override
			public boolean check(Book book){
				return bookName.equals(book.getName());
			}
		});
		System.out.println("[익명클래스] 책 이름이 computer1 인 책의 수 : " + results.size());
		
		/* Lambda에 의한 함수 파라미터 전달 */
		String type = "computer";
		results = LambdaMain.collectBookByLambda(bookList, (g) -> {return type.equals(g.getType());});
		System.out.println("[람다] 종류가 computer 인 책의 수 : " + results.size());
		
		/* 
			Free Variable type can't be reuse because of the variable is used in the lambda expression
			Variable Capture(변수 포획)
		*/
		String type2 = "history";
		results = LambdaMain.collectBookByStream(bookList, (Book b) -> type2.equals(b.getType()));
		System.out.println("[스트림] 종류가 history 인 책의 수 : " + results.size());

    }
	
	 public static List<Book> collectBook(List<Book> bookList, int price) {
        List<Book> resultBook = new ArrayList<>();
        for (Book book : bookList) {
            if (book.getPrice() >= price) {
                resultBook.add(book);
            }
        }
        return resultBook;
    }
	
	/* Algorithm injection via function interface */
	public static List<Book> collectBookByLambda(List<Book> bookList, BookPredicate predicate){
		List<Book> resultBook = new ArrayList<>();
        for (Book book : bookList) {
            if (predicate.check(book)) {
                resultBook.add(book);
            }
        }
        return resultBook;
	}

	/* Collection operation using Java8 Stream API */
	public static List<Book> collectBookByStream(List<Book> bookList, Predicate<Book> predicate){
		return bookList.stream().filter(predicate).collect(Collectors.toList());
	}
}
