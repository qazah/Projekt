package com.Darek.Programik.service;

import com.Darek.Programik.model.BookEntity;

import com.Darek.Programik.model.BookInBasket;
import com.Darek.Programik.repository.BasketRepository;
import com.Darek.Programik.repository.BookRepository;
import com.Darek.Programik.utils.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Scanner;

@Service
public class UserService {



    @Autowired
    BookRepository bookRepository;

    @Autowired
    BasketRepository basketRepository;

    @Autowired
    Tools tools;

    public void addToBasket() {
        System.out.println("Podaj ID ksiązki którą chcesz dodać do koszyka tralala");

        Scanner entry = new Scanner(System.in);
        long id = entry.nextInt();

        BookEntity book = bookRepository.getBookById(id);

        if (book == null) {
            System.out.println("Nie znaleziono książki o takim ID");
            return;
        }

        System.out.println("Podaj ilość książek jakie chcesz dodać");

        Scanner entry1 = new Scanner(System.in);
        int quantity = entry1.nextInt();

        if (book.getQuantity() <= quantity) {
            System.out.println("Za mało książek w magazynie");
            return;
        }

        basketRepository.addBookToBasket(id, quantity);

        book.calculatingQuantity(quantity);

        tools.printContinue();
    }

    public void deleteFromBasket() {
        System.out.println("Podaj ID ksiązki którą chcesz usunąć z  koszyka");

        Scanner entry = new Scanner(System.in);
        long id = entry.nextInt();

        BookInBasket bookInBasket = basketRepository.getBook(id);

        if (bookInBasket == null) {
            System.out.println("Nie znaleziono książki o takim ID");
            return;
        }


        long idBook = bookInBasket.getIdBook(id); // pobieram Id Książki z koszyka
        BookEntity book = bookRepository.getBookById(idBook); // tworzę instancję ksiązki
        book.addingQuantity(bookInBasket.getQuantity()); // dodaję ilośc książek do magazynu

        basketRepository.deleteFromBasket(bookInBasket);


        tools.printContinue();
    }


    public void showBookList() {
        List<BookInBasket> basket = basketRepository.findAllBooks();
        float totalPrice = totalPrice(basket);
        if (basket.size() < 1) {
            System.out.println("Narazie nie ma żadnej ksiazki");
        } else {
            System.out.println();
            for (BookInBasket wynik : basket) {
                System.out.println(wynik);
            }
        System.out.println("Cena za cały koszyk: " + totalPrice);
        }
    }

    public void specificInformation() {
        System.out.println("Podaj ID książki ktorej chcesz poznać danettt:");

        Scanner entry = new Scanner(System.in);
        int id = entry.nextInt();

        BookInBasket bookInBasket = basketRepository.getBook(id);

        if (bookInBasket == null) {
            System.out.println("Nie znaleziono książki o takim ID");
            return;
        }

        long idBook = bookInBasket.getIdBook(id);//pobieram IDKsiążki po ID koszyka, aby potem stworzyć instancje książki po tym ID
        BookEntity book = bookRepository.getBookById(idBook);

        System.out.println("Tytuł: " + book.getTitle());
        System.out.println("Autor: " + book.getAuthor());
        System.out.println("Rodzaj: " + book.getType());
        System.out.println("Ilość: " + bookInBasket.getQuantity());
        System.out.println("Cena: " + book.getPrice());
        float totalPriceBook = (bookInBasket.getPrice()) * (bookInBasket.getQuantity());
        System.out.println("Łączna cena za książki: " + totalPriceBook);
        tools.printContinue();
    }


    public Float totalPrice(List<BookInBasket> basket){
        float totalPrice = 0;
        for (BookInBasket wynik : basket) {
            totalPrice = wynik.getPrice() * wynik.getQuantity() + totalPrice ;
        }
      return totalPrice;
    }
}
