import React, { useState } from "react";
import Button from "@mui/material/Button";
import TextField from '@mui/material/TextField';
import Box from "@mui/material/Box";
import axios from 'axios'
import useUserStore from "../../stores/useUserStore"

const CreateReply = (props) => {
    const { setShowReply, parent, postId, addComment } = props;
    const [comment, setComment] = useState("")
    const { user } = useUserStore();
    const headers = {
        'Authorization': user.accessToken
      }
    const handleAddReply  = async(e) => {
        e.preventDefault(); 
        
        const data = {
           content : comment,
           userId  : user.id,
           postId  : postId, 
           parent  : parent
        }
         
        try {
            const result= await axios.post(`http://localhost:8080/api/comments/create/`,data,headers);
            setShowReply(false)
            addComment(result.data)
        } catch (error) {
            console.log(error);
        }
    };

    return (
        <div>
          <form style={{ display: 'flex' }}>
                <Box width={"100%"}>
                    <TextField
                        fullWidth
                        label="댓글을 입력해주세요"
                        type="text"
                        variant="standard"
                        value={comment}
                        onChange={(e) => setComment(e.target.value)}
                        />
                </Box>
                <Button style={{ color:'white', width: '30px', height: '47.5px'  ,backgroundColor:'#2E7D32'}} onClick={handleAddReply}>저장</Button>
          </form>
        </div>
    );
};

export default CreateReply;