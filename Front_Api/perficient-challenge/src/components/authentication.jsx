import 'bootstrap/dist/css/bootstrap.min.css';
import React from 'react'
import { useEffect, useState } from 'react'
//import { Form, FormGroup, Button, Label, Input, FormText} from 'react-bootstrap'
import PropTypes from 'prop-types'
import axios from  '../config/axios'
import { Link } from 'react-router-dom';
import { useNavigate } from 'react-router-dom';
import background from "../img/login.jpeg";




const authentication = () => {

    return(
        <>
            <div class="d-flex justify-content-center align-items-center vh-100" style={{ backgroundImage: `url(${background})`, backgroundSize: 'cover', backgroundRepeat: 'no-repeat', backgroundPosition: 'center center'}}>
                <div class="container col-3" style={{ backgroundColor: 'white' }}>
                    <div class="row">
                        <div class="col-12 text-center">
                            <h1 class="display-1">Login</h1>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-12 mx-auto">
                            <div class="input-group mb-3">
                                <input type="text" class="form-control form-control rounded-pill" placeholder="User name"/>
                            </div>
                            <div class="input-group mb-3">
                                <input type="text" class="form-control form-control rounded-pill" placeholder="Password"/>
                            </div>
                            
                        </div>
                    </div> 
                    <div class="row">
                        <div class="col-12 mx-auto">
                            <div class="d-grid gap-2 mb-3">
                                    <button type="button" class="btn btn-outline-primary btn-block rounded-pill">Login</button>
                                </div>
                                <div class="d-grid gap-2 mb-3">
                                    <button type="button" class="btn btn-outline-secondary btn-block rounded-pill">Register</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </>
    )

}

export default authentication