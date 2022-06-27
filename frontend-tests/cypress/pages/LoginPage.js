class LoginPage {
    
    go() {
        cy.visit('/login')
    }

    fillForm(user) {
        cy.get('#usuario').type(user.name)
        cy.get('#password').type(user.password)
    }

    submit() {
        cy.get('.MuiButton-root').click()
    }

}

export default LoginPage;