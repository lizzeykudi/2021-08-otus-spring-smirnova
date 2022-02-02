import React from 'react'

const Header = (props) => (<h1>{props.title}</h1>);

export default class App extends React.Component {


    constructor() {
        super();
        this.defaultBook = {title: "", authorId: -1, bookGenreId: -1};
        this.state = {
            books: [],
            authors: [],
            bookGenres: [],
            newBookTitle: '',
            newBookId: undefined,
            newBookAuthorHref: -1,
            newBookGenreHref: -1,
            editBookTitle: '',
            editBookAuthorHref: -1,
            editBookGenreHref: -1,
            warningVisible: false,
            editBook: -1
        };

        this.setBookTitle = this.setBookTitle.bind(this);
        this.setBookAuthor = this.setBookAuthor.bind(this);
        this.setBookGenre = this.setBookGenre.bind(this);
        this.editBookTitle = this.editBookTitle.bind(this);
        this.editBookAuthor = this.editBookAuthor.bind(this);
        this.editBookGenre = this.editBookGenre.bind(this);
    }

    componentDidMount() {
        this.refreshBooks();
        this.refreshAuthors();
        this.refreshBookGenres();

    }

    render() {
        return (<React.Fragment>
            <Header title={'Books'}/>
            <table>
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Title</th>
                    <th>Author</th>
                    <th>BookGenre</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody>
                {this.state.books.map((book, i) => (
                    <tr key={i}>
                        {(i === this.state.editBook) ?
                            <React.Fragment>
                                <td></td>
                                <td><input id="name-input" name="title" type="text" value={this.state.editBookTitle}
                                           onChange={this.editBookTitle}/></td>
                                <td>
                                    <select className="form-control" name="author" value={this.state.editBookAuthorHref}
                                            onChange={this.editBookAuthor}>
                                        {this.state.authors.map((author, i) => (
                                            <option value={author._links.self.href}>{author.name}</option>))}
                                        <option value={-1}></option>
                                    </select>
                                </td>
                                <td>
                                    <select className="form-control" name="bookGenre" value={this.state.editBookGenreHref}
                                            onChange={this.editBookGenre}>
                                        {this.state.bookGenres.map((bookGenre, i) => (
                                            <option value={bookGenre._links.self.href}>{bookGenre.title}</option>))}
                                        <option value={-1}></option>
                                    </select>
                                </td>
                                <td>
                                    <button onClick={() => this.saveEditedBook()}>Save</button>
                                </td>
                            </React.Fragment> :
                            <React.Fragment>
                                <td>{book.id}</td>
                                <td>{book.title}</td>
                                <td>{book.author ? book.author.name : ''}</td>
                                <td>{book.bookGenre ? book.bookGenre.title : ''}</td>
                                <td>
                                    <button onClick={() => this.editBook(i)}>Edit</button>
                                </td>
                                <td>
                                    <button onClick={() => this.deleteBook(i)}>Delete</button>
                                </td>
                            </React.Fragment>}
                    </tr>))}

                <tr>
                    <td></td>
                    <td><input id="name-input" name="title" type="text" value={this.state.newBookTitle}
                               onChange={this.setBookTitle}/></td>
                    <td>
                        <select className="form-control" name="author" value={this.state.newBookAuthorHref}
                                onChange={this.setBookAuthor}>
                            {this.state.authors.map((author, i) => (
                                <option value={author._links.self.href}>{author.name}</option>))}
                            <option value={-1}></option>
                        </select>
                    </td>
                    <td>
                        <select className="form-control" name="bookGenre" value={this.state.newBookGenreHref}
                                onChange={this.setBookGenre}>
                            {this.state.bookGenres.map((bookGenre, i) => (
                                <option value={bookGenre._links.self.href}>{bookGenre.title}</option>))}
                            <option value={-1}></option>
                        </select>
                    </td>
                </tr>

                </tbody>
            </table>
            {this.state.warningVisible ? <div>Fill all required fields</div> : null}
            <button id="save-button" onClick={() => this.saveBook()}>Save</button>
        </React.Fragment>)
    }

    setBookTitle(event) {
        this.setState({newBookTitle: event.target.value});
    }

    setBookAuthor(event) {
        this.setState({newBookAuthorHref: event.target.value});
    }

    setBookGenre(event) {
        this.setState({newBookGenreHref: event.target.value});
    }

    editBookTitle(event) {
        this.setState({editBookTitle: event.target.value});
    }

    editBookAuthor(event) {
        this.setState({editBookAuthorHref: event.target.value});
    }

    editBookGenre(event) {
        this.setState({editBookGenreHref: event.target.value});
    }

    saveBook() {
        if (this.state.newBookTitle === '' || this.state.newBookAuthorHref === -1 || this.state.newBookGenreHref === -1) {
            this.setState({warningVisible: true})
            return;
        } else {
            this.setState({warningVisible: false})
            const newBook = {
                title: this.state.newBookTitle,
            }
            fetch(`/books`, {
                method: 'POST', headers: {
                    'Accept': 'application/json', 'Content-Type': 'application/json'
                }, body: JSON.stringify(newBook),
            })
                .then(response => response.json())
                .then((result) => this.setState({newBookId: result.id}))
                .then( () =>
                    fetch('/books/'+this.state.newBookId+'/author', {
                        method: 'PUT', headers: {
                            'Accept': 'application/json', 'Content-Type': 'text/uri-list'
                        }, body: this.state.newBookAuthorHref
                    })
                ).then( () =>
                    fetch('/books/'+this.state.newBookId+'/bookGenre', {
                        method: 'PUT', headers: {
                            'Accept': 'application/json', 'Content-Type': 'text/uri-list'
                        }, body: this.state.newBookGenreHref
                    })
                ).then( () => {
                    this.refreshBooks();
                    this.resetBook();
                })
        }
    }

    saveEditedBook() {
        if (this.state.editBookTitle === '' || this.state.editBookAuthorHref === -1 || this.state.editBookGenreHref === -1) {
            this.setState({warningVisible: true})
        } else {
            this.setState({warningVisible: false})
            const editBook = {
                title: this.state.editBookTitle,
            }
            fetch(`/books/` + this.state.books[this.state.editBook].id, {
                method: 'PUT', headers: {
                    'Accept': 'application/json', 'Content-Type': 'application/json'
                }, body: JSON.stringify(editBook),
            }).then( () =>
                fetch('/books/'+this.state.books[this.state.editBook].id+'/author', {
                    method: 'PUT', headers: {
                        'Accept': 'application/json', 'Content-Type': 'text/uri-list'
                    }, body: this.state.editBookAuthorHref
                })
            ).then( () =>
                fetch('/books/'+this.state.books[this.state.editBook].id+'/bookGenre', {
                    method: 'PUT', headers: {
                        'Accept': 'application/json', 'Content-Type': 'text/uri-list'
                    }, body: this.state.editBookGenreHref
                })
            ).then((result) => {
                this.refreshBooks();
                this.resetBook();
                this.setState({editBook: -1})
            });
        }
    }

    deleteBook(i) {
        fetch(`/books/` + this.state.books[i].id, {
            method: 'DELETE', headers: {
                'Accept': 'application/json', 'Content-Type': 'application/json'
            },
        }).then((result) => {
            if (result.status === 403) alert("access denied")
            this.refreshBooks();
        });
    }

    editBook(i) {
        this.setState((prevState, prevProps) => {
            return {
                editBook: i,
                editBookTitle: prevState.books[i].title,
                editBookAuthorHref: prevState.books[i].author.id,
                editBookGenreHref: prevState.books[i].bookGenre.id
            }
        })
    }


    refreshBooks() {
        fetch('/books')
            .then(response => response.json())
            .then(response => Promise.all(
                response._embedded.books.map(
                    book => Promise.all([
                            Promise.resolve(book),
                            fetch(book._links.author.href).then(resp => resp.json()),
                            fetch(book._links.bookGenre.href).then(resp => resp.json())
                    ])
                )
            )).then(results => this.setState({
                books: results.map(([book, author, bookGenre]) => ({
                        id: book.id,
                        title: book.title,
                        author: author,
                        bookGenre: bookGenre
                    })
                )
            }))
    }

    refreshAuthors() {
        fetch('/authors')
            .then(response => response.json())
            .then(authors => this.setState({authors: authors._embedded.authors}))
            .then(() => console.log(this.state));
    }

    refreshBookGenres() {
        fetch('/bookGenres')
            .then(response => response.json())
            .then(authors => this.setState({bookGenres: authors._embedded.bookGenres}))
            .then(() => console.log(this.state));
    }

    resetBook() {
        this.setState({
            newBookId: undefined,
            newBookTitle: this.defaultBook.title,
            newBookAuthorHref: this.defaultBook.authorId,
            newBookGenreHref: this.defaultBook.bookGenreId
        });
    };
}