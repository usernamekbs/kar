import React, { useState } from "react";
import Button from "@mui/material/Button";
import TextField from '@mui/material/TextField';
import Box from "@mui/material/Box";
import axios from 'axios'
import useUserStore from "../../stores/useUserStore"

const CreateComment = (props) => {
    const [comment, setComment] = useState("");   
    const { user } = useUserStore();
    const headers = {
      'Authorization': user.accessToken
    }
    const handleChange = (e) => {
        setComment(e.currentTarget.value)  
    }

    const handleAddComment = async(e) => {
        e.preventDefault();
        
        const data = {
           content  : comment,
           userId : user.id,
           postId   : props.postId, 
           parent   : null
        }
         
        try {
            const result= await axios.post(`http://localhost:8080/api/comments/create/`,data,headers);
            props.refreshFunction(result.data)
            setComment('')
        } catch (error) {
            console.log(error);
        }
    };
    
      return (  
        <div>
        
          <form style={{ display: 'flex' }}>
            <Box height={"50vh"} width={"100%"}>
                    <TextField
                        fullWidth
                        label="댓글"
                        type="text"
                        variant="standard"
                        value={comment}
                        onChange={handleChange} 
                        />
            </Box>
            <Button style={{ color:'white', width: '30px', height: '47.5px' ,backgroundColor:'#2E7D32'}} onClick={handleAddComment}>저장</Button>
          </form>
        </div>  
      ); 


};


export default CreateComment;