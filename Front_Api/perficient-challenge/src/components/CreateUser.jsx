import 'bootstrap/dist/css/bootstrap.min.css';
import React from 'react'
import { useEffect, useState } from 'react'
//import { Form, FormGroup, Button, Label, Input, FormText} from 'react-bootstrap'
import PropTypes from 'prop-types'
import axios from  '../config/axios'
import { Link } from 'react-router-dom';
import { useNavigate } from 'react-router-dom';
import background from "../img/register.jpeg";

const CreateUser = ({createUser}) => {
    const[userName, setUserName] = useState('')
    const[password, setPassword] = useState('')
    const[name, setName] = useState('')
    const[apellido,setApellido] = useState('')
    const[pais, setPais] = useState('')

    let navigate = useNavigate();

    const handleClick = () =>{
        createUser({userName,password,name,apellido,pais})
        navigate('/')
    }

    return(

        <div class="d-flex justify-content-center align-items-center vh-100" style={{ backgroundImage: `url(${background})`, backgroundSize: 'cover', backgroundRepeat: 'no-repeat', backgroundPosition: 'center center'}}>

                <div class="container text-center">
                    <div class="row justify-content-end align-items-center" >
                        <div class="col-4 tex-center" style={{ backgroundColor: 'white' }}>
                            <h1 class="display-1">Register</h1>
                        </div>
                    </div>
                    <div class="row justify-content-end align-items-center">
                        <div class="col-4"  style={{ backgroundColor: 'white' }}>
                            <div class="input-group mb-3">
                                <input type="text" class="form-control form-control rounded-pill" placeholder="User name" onChange={(e)=>{setUserName(e.target.value)}}/>
                            </div>
                            <div class="input-group mb-3">
                                <input type="password" class="form-control form-control rounded-pill" placeholder="Password" onChange={(e)=>{setPassword(e.target.value)}}/>
                            </div>
                            <div class="input-group mb-3">
                                <input type="text" class="form-control form-control rounded-pill" placeholder="Nombre" onChange={(e)=>{setName(e.target.value)}}/>
                            </div>
                            <div class="input-group mb-3">
                                <input type="text" class="form-control form-control rounded-pill" placeholder="Apellido" onChange={(e)=>{setApellido(e.target.value)}}/>
                            </div>
                            <div class="input-group mb-3">
                                <input type="text" class="form-control form-control rounded-pill" placeholder="Pais" onChange={(e)=>{setPais(e.target.value)}}/>
                            </div>
                        </div>
                    </div>
                    <div class="row justify-content-end align-items-center">
                        <div class="col-4"  style={{ backgroundColor: 'white' }}>
                            <div class="d-grid gap-2 mb-3">
                                <button type="button" class="btn btn-outline-primary btn-block rounded-pill" onClick={handleClick}>Crear</button>
                            </div> 
                        </div>
                    </div>
                </div>
            
            </div>
    )
}

export default CreateUser