class AddBookPage {

    add(book) {

        cy.get('#name').type(book.name)
        cy.get('#author').type(book.author)
        cy.get('#price').type(book.price)

        cy.get('.MuiButton-contained').click()
       
    }

    alertTextShouldBe(expectedMesssage) {
        cy.get('.Toastify__toast-body > :nth-child(2)').should('have.text', expectedMesssage)

    }
        
}

export default AddBookPage;