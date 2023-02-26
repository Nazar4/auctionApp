.model small
.386
.stack

.data
surname       BYTE  "Kulish"
nam              BYTE  "Nazar"
patronymic  BYTE  "Yuriyovych"

row    	   BYTE  6
column           BYTE  41

color equ 0Ah
ATTRIB_HI = 10000000b

.code
main PROC
;---------------------- init ------------------------
	call clrscr
	mov  ax, @data
	mov  ds, ax

;	mov color, (010b SHL 4) OR blue
	mov cx, SIZEOF surname
	mov si, OFFSET surname
	mov dh, row		; start row
	mov dl, column          ; start column
	call writeStr

;	mov color, (0101b SHL 4) OR 100b
	mov cx, SIZEOF nam
	mov si, OFFSET nam
	mov dh, row		;row + 2
	add dh, 2
	mov dl, column          ;column + 2
	add dl, 3
	call writeStr

;	mov color, (110b SHL 4) OR 111b
	mov cx, SIZEOF patronymic
	mov si, OFFSET patronymic
	mov dh, row		; start row
	add dh, 6
	mov dl, column          ; start column
	add dl, 8
	call writeStr

	mov  ah, 2
	mov  dx, 0
	int  10h
	.exit
main ENDP



writeStr proc
	mov ah, 2                               ;set cursor
	int 10h

	L1:
	push cx
	mov ah, 9
	mov al, [si]
	mov bh, 0
	mov bl, color
	or bl, ATTRIB_HI
	mov cx, 1
	int 10h
	mov cx, 1
	call AdvanceCursor
;	inc color
	inc si
	pop cx
	loop L1
	ret
writeStr endp



clrscr proc
	mov ax, 0b800h
	mov es, ax
	mov di, 0
	mov al, ' '
	mov ah, 07d
	loop_clear_12:
	mov word ptr es:[di], ax
	inc di
	inc di
	cmp di, 4000
	jle loop_clear_12
	ret
clrscr endp


AdvanceCursor PROC
	pusha
L2:
	push cx
	mov ah, 3
	mov bh, 0
	int 10h
	inc dl
	mov ah, 2
	int 10h
	pop cx
	loop L2
	popa
	ret
AdvanceCursor ENDP


EnableBlinking PROC
	push ax
	push bx
	mov ax, 1003h
	mov bl, 1
	int 10h
	pop bx
	pop ax
	ret
	EnableBlinking ENDP

END main