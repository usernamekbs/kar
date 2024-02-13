import React, { useState, useEffect } from 'react';
import { styled } from '@mui/material/styles';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell, { tableCellClasses } from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';
import axios from "axios";
import { Link } from 'react-router-dom';
import useUserStore from "../../stores/useUserStore"

const User = () => {
    const [userList,setUserList] = useState([]);
    const { user } = useUserStore();

    const StyledTableCell = styled(TableCell)(({ theme }) => ({
        [`&.${tableCellClasses.head}`]: {
          backgroundColor: theme.palette.common.black,
          color: theme.palette.common.white,
        },
        [`&.${tableCellClasses.body}`]: {
          fontSize: 14,
        },
      }));
      
      const StyledTableRow = styled(TableRow)(({ theme }) => ({
        '&:nth-of-type(odd)': {
          backgroundColor: theme.palette.action.hover,
        },
        '&:last-child td, &:last-child th': {
          border: 0,
        },
      }));
      
    useEffect(() => {
        const fetchUser = async () => {
            try {
              const result = await axios.get(`http://localhost:8080/api/user/list/`
              ,{ headers: {"Authorization" : `Bearer ${user.accessToken}`} }
              );
              setUserList(result.data)   
            } catch (error) {
              console.log(error);
            }
          };
          fetchUser();
    }, []);  

    return (
      <>
        <TableContainer component={Paper}>
        <Table sx={{ minWidth: 700 }} aria-label="customized table">
            <TableHead>
            <TableRow>
                <StyledTableCell>유저 번호</StyledTableCell>
                <StyledTableCell align="right">유저 아이디</StyledTableCell>
                <StyledTableCell align="right">유저 닉네임</StyledTableCell>
                <StyledTableCell align="right">유저 이메일</StyledTableCell>
            </TableRow>
            </TableHead>
            <TableBody>
            {userList.map((user) => (
                <StyledTableRow key={user.id}>
                  <StyledTableCell component="th" scope="row">
                      <Link to={`/user/view/${user.id}`}>{user.id}</Link>
                  </StyledTableCell>
                  <StyledTableCell align="right">{user.username}</StyledTableCell>
                  <StyledTableCell align="right">{user.nickname}</StyledTableCell>
                  <StyledTableCell align="right">{user.email}</StyledTableCell>
                </StyledTableRow>
            ))}
            
            </TableBody>
        </Table>
        </TableContainer>
      </>
      );

}
export default User;