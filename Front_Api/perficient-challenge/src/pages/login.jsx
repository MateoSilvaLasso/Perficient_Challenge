import React from 'react'
import Authentication from '../components/authentication'
import axios from  '../config/axios'
import { context } from '../context/context'
import { useEffect, useState } from 'react'

const login = () => {

  const[userEdit, setUserEdit] = useState({token:""})

  const login = async (user) =>{
    try{
        const res = await axios.post("/login/create", user)
        //console.log(user)
        if(res.status == 200){
          setUserEdit(res.data);
          console.log(res.status)
        }
        console.log(res.data)
        console.log(userEdit)
        
        //console.log(res.status)
    }catch(e){
      console.log(e);
    }
  }

  useEffect

  return (
    <context.Provider>
          <Authentication addUser={login} userEdit={userEdit}/>
    </context.Provider>
  )
}

export default login
