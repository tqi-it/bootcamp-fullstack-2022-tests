class LoginPage {
    
    go() {
        cy.visit('/login')
        //cy.get('.MuiTypography-h6').should('have.text', 'Faça seu Login')
    }

    // dataLayerContentShouldBe(expectedText) {
    //     cy.get('.MuiTypography-h6').should('have.text', expectedText)

    // }

    //cy.get('.MuiBox-root > .MuiTypography-root').should('have.text', 'Livros')

    fillForm(user) {
        cy.get('#usuario').type(user.name)
        cy.get('#password').type(user.password)
    }

    submit() {
        cy.get('.MuiButton-root').click()
    }

}

export default LoginPage;