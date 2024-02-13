import React, { useState } from "react";
import Button from "@mui/material/Button";
import TextField from '@mui/material/TextField';
import Box from "@mui/material/Box";
import axios from "axios"
import useUserStore from "../../stores/useUserStore"

const UpdateReply = (props) => {
    const { setShowReply,  updateComment, setEdit } = props;
    const [comment, setComment] = useState(props.comments.content)
    const { user } = useUserStore();
    const headers = {
        'Authorization': user.accessToken
      }

    const handleUpdateReply  = async(e) => { 
        e.preventDefault(); 
          
        const data = {
          id        : props.comments.id,
          postId    : props.comments.postId,  
          content   : comment,
          userId    : user.id,
          parent    : props.comments.parent
        }; 
           
        try {
            const result= await axios.put(`http://localhost:8080/api/comments/update/`+props.comments.id,data,headers);
            updateComment(result.data) 
            setComment('')
            setShowReply(false)
            setEdit(false)
        } catch (error) {
            console.log(error);
        }
    };

   

    return (
        <>
            
            <form style={{ display: 'flex' }}>
                <Box>
                    <TextField
                        fullWidth
                        label="댓글을 입력해주세요"
                        type="text"
                        variant="standard"
                        value={comment}
                        onChange={(e) => setComment(e.target.value)}
                        />
                </Box>
                <Button variant="contained" color="success" style={{ width: '30px', height: '47.5px' }} onClick={handleUpdateReply}>저장</Button>
            </form>
           
        </>
    );
};

export default UpdateReply;