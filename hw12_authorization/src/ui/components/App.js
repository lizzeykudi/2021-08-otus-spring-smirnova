import React, {Fragment} from 'react'

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
            newBookAuthorId: -1,
            newBookGenreId: -1,
            editBookTitle: '',
            editBookAuthorId: -1,
            editBookGenreId: -1,
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
                                    <select className="form-control" name="author" value={this.state.editBookAuthorId}
                                            onChange={this.editBookAuthor}>
                                        {this.state.authors.map((author, i) => (
                                            <option value={author.id}>{author.name}</option>))}
                                        <option value={-1}></option>
                                    </select>
                                </td>
                                <td>
                                    <select className="form-control" name="bookGenre" value={this.state.editBookGenreId}
                                            onChange={this.editBookGenre}>
                                        {this.state.bookGenres.map((bookGenre, i) => (
                                            <option value={bookGenre.id}>{bookGenre.title}</option>))}
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
                        <select className="form-control" name="author" value={this.state.newBookAuthorId}
                                onChange={this.setBookAuthor}>
                            {this.state.authors.map((author, i) => (
                                <option value={author.id}>{author.name}</option>))}
                            <option value={-1}></option>
                        </select>
                    </td>
                    <td>
                        <select className="form-control" name="bookGenre" value={this.state.newBookGenreId}
                                onChange={this.setBookGenre}>
                            {this.state.bookGenres.map((bookGenre, i) => (
                                <option value={bookGenre.id}>{bookGenre.title}</option>))}
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
        this.setState({newBookAuthorId: event.target.value});
    }

    setBookGenre(event) {
        this.setState({newBookGenreId: event.target.value});
    }

    editBookTitle(event) {
        this.setState({editBookTitle: event.target.value});
    }

    editBookAuthor(event) {
        this.setState({editBookAuthorId: event.target.value});
    }

    editBookGenre(event) {
        this.setState({editBookGenreId: event.target.value});
    }

    saveBook() {
        if (this.state.newBookTitle === '' || this.state.newBookAuthorId === -1 || this.state.newBookGenreId === -1) {
            this.setState({warningVisible: true})
            return;
        } else {
            this.setState({warningVisible: false})
            const newBook = {
                title: this.state.newBookTitle,
                authorId: Number(this.state.newBookAuthorId),
                bookGenreId: Number(this.state.newBookGenreId)
            }
            fetch(`/api/books`, {
                method: 'POST', headers: {
                    'Accept': 'application/json', 'Content-Type': 'application/json'
                }, body: JSON.stringify(newBook),
            }).then((result) => {
                this.refreshBooks();
                this.resetBook();
            });
        }
    }

    saveEditedBook() {
        if (this.state.editBookTitle === '' || this.state.editBookAuthorId === -1 || this.state.editBookGenreId === -1) {
            this.setState({warningVisible: true})
        } else {
            this.setState({warningVisible: false})
            const editBook = {
                title: this.state.editBookTitle,
                authorId: Number(this.state.editBookAuthorId),
                bookGenreId: Number(this.state.editBookGenreId)
            }
            fetch(`/api/books/`+this.state.books[this.state.editBook].id, {
                method: 'PUT', headers: {
                    'Accept': 'application/json', 'Content-Type': 'application/json'
                }, body: JSON.stringify(editBook),
            }).then((result) => {
                this.refreshBooks();
                this.resetBook();
                this.setState({editBook: -1})
            });
        }
    }

    deleteBook(i) {
            fetch(`/api/books/`+this.state.books[i].id, {
                method: 'DELETE', headers: {
                    'Accept': 'application/json', 'Content-Type': 'application/json'
                },
            }).then((result) => {
                if(result.status===403) alert("access denied")
                this.refreshBooks();
            });
    }

    editBook(i) {
        this.setState((prevState, prevProps) => {
            return {
                editBook: i,
                editBookTitle: prevState.books[i].title,
                editBookAuthorId: prevState.books[i].author.id,
                editBookGenreId: prevState.books[i].bookGenre.id
            }
        })
    }



    refreshBooks() {
        fetch('/api/books')
            .then(response => response.json())
            .then(books => this.setState({books: books}));
    }

    refreshAuthors() {
        fetch('/api/authors')
            .then(response => response.json())
            .then(authors => this.setState({authors: authors}));
    }

    refreshBookGenres() {
        fetch('/api/bookGenres')
            .then(response => response.json())
            .then(bookGenres => this.setState({bookGenres: bookGenres}));
    }

    resetBook() {
        this.setState({
            newBookTitle: this.defaultBook.title,
            newBookAuthorId: this.defaultBook.authorId,
            newBookGenreId: this.defaultBook.bookGenreId
        });
    };
}