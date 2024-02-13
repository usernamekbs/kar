import React, { useState } from 'react';
import useUserStore from "../../stores/useUserStore"
import axios from "axios";
import CreateReply from './CreateReply'
import UpdateReply from './UpdateReply'


const Comment = (props) => {
    const { comments, setPost} = props
    const [edit,setEdit] = useState(false) 
    const [showChildReply, setShowChildReply] = useState(false)
    const [showReply, setShowReply] = useState(false)
    const comment = comments
    const { user } = useUserStore();

    const handleDeleteReply  = async () => { 
        
      try {
          const result = await axios.delete(`http://localhost:8080/api/comments/delete/`+props.comments.id,{ headers: {"Authorization" : `Bearer ${user.accessToken}`} });
          alert(result.data.message)
 
        } catch (error) {
          console.log(error);
      }
  };

    const handleReplyComment = () => { 
      {
          showReply ===false ? setShowReply(true) : setShowReply(false)
      }
    }

    const recursiveAddComment = (prevComment,...newComment) => {
        let updatedComments = prevComment
        const id = comment.id
        
        updatedComments.children && updatedComments.children.map((c)=>
            id === c.id ? c.children.push(...newComment) : recursiveAddComment(c,...newComment)
        )
        
    } 

    const addComment = (newComment) => {
      const parent = comment.parent
      const id = comment.id 
      if(parent !== null){  
        setPost((post)=>{
          post.comments.map((c)=>{
            recursiveAddComment(c,newComment)
            return c
          })
          return post
        })
      }else{
        setPost((post)=>{  
          post.comments.map((c)=>{
              if(c.id === id){ 
                return c.children.push(newComment) 
              }   
          })
          return post 
        }) 
      } 
    } 

    const recursiveUpdateComment = (prevComment,newComment) =>{
      const id = comment.id

      prevComment.children && prevComment.children.map((c)=>{
        id === c.id ? c.content = newComment.content : recursiveUpdateComment(c,newComment)
      })

    }

    const updateComment = (newComment) => {
      const parent = comment.parent
      const id = comment.id 

      if(parent !== null){  
        setPost((post) => {
          post.comments.map((c)=>{
              recursiveUpdateComment(c,newComment)
              return c
          })

          return post
        })
      }else{
        setPost((post) => {
          post.comments.map((c)=>{ 
            if(id===c.id){ 
              return c.content = newComment.content  
            } 
          })

          return post
        })  
      } 
    }  

    const recursiveDeleteComment = (prevComment) => { 
        const id = comment.id
        const children = comment.children
        
        return prevComment.map( c => { return {...c}}).filter(c=> {
          if(children!==0){ 
            c.children = recursiveDeleteComment(c.children)
          } 
 
          return c.id !== id
        })
    
    } 

    const deleteComment = () =>{
        let updateComments={}; 
        setPost((post) => {
          updateComments = recursiveDeleteComment(post.comments)   
          post.comments = updateComments 
          return {...post} 
        })
        handleDeleteReply()
    }

    return (  
      <div style={{ paddingLeft: 10 }}>  
          {edit===false ?      
                <>
                    <span>번호 : {comment.id}</span>
                    <br></br>    내용 : <span>{comment.content}</span>
                    <br></br>    작성자 : <span>{comment.username}</span>
                    <div>
                    <span onClick={handleReplyComment} style={{cursor:'grab',color: "black",marginLeft:10}}>댓글 쓰기</span>
                    {user && comment.username === user.username &&
                      <>
                        <span onClick={() => setEdit((show) => !show)} style={{cursor:'grab',color: "blue"}}>댓글 수정</span>
                        <span onClick={() => deleteComment(comment.id)} type="danger" style={{cursor:'grab',color: "red"}}>댓글 삭제</span>
                      </>
                    } 
                    </div>
                </>
                :
                <UpdateReply
                    setEdit={setEdit}
                    setShowReply={setShowReply}
                    comments={comment}
                    updateComment={updateComment}
                />
          }

          {showReply &&   
                <CreateReply
                    parent={comment.id}
                    postId={comment.postId}
                    setShowReply={setShowReply}
                    addComment={addComment}
                />
          }
        <div onClick={() => setShowChildReply((show) => !show)}  style={{cursor: 'grab'}}>  
            <span style={{color:'red',marginLeft:'10px'}}>{comment.children.length}개의 댓글 더보기</span> 
        </div>
        

        {showChildReply && comments.children && comments.children.map((comment) => {
          return (
            <div key={comment.id} style={{ paddingLeft: 10 }}>
                 <Comment 
                    comments={comment}
                    setPost={setPost}
                 />
            </div>
          );
        })}
     
      </div> 
    );
}

export default Comment;