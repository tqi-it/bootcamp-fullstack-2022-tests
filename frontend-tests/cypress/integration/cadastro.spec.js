import LoginPage from '../pages/LoginPage'
import BookPage from '../pages/BookPage'
import AddBookPage from '../pages/AddBookPage'

var loginPage = new LoginPage()
var bookPage = new BookPage()
var addBookPage = new AddBookPage()

before(function() {
    cy.fixture('book').then((book) => {
        this.book = book
    })

    cy.fixture('user').then((user) => {
        this.user = user
    })

})

describe('cadastro de livros', () => {
    it('deve cadastrar um livro', function() {
        
        const expectedMessage = 'Registro salvo com sucesso!'
       
        loginPage.go()
        loginPage.fillForm(this.user.user)
        loginPage.submit()
        
        bookPage.addBook()

        addBookPage.add(this.book.valid_book)
        
        addBookPage.alertTextShouldBe(expectedMessage)

    })

    it('não deve cadastrar um livro quando não for informado o nome', function() {
        const expectedMessage = 'Preencha os campos do formulário!'
       
        loginPage.go()
        loginPage.fillForm(this.user.user)
        loginPage.submit()
        
        bookPage.addBook()

        addBookPage.add(this.book.empty_book_name)
        
        addBookPage.alertTextShouldBe(expectedMessage)
    })
})
