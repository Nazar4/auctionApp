INCLUDE Irvine16.inc
.data
	saveMode	BYTE	? 		; Збережений відеорежим
	xVal		WORD	5
	yVal		WORD	8
	offset_         word    ?
	; В основній процедурі встановлюється відеорежим 13h, колір фону,
	; виводиться кілька пікселів на екран, а потім відновлюється відеорежим.
.code
main PROC
	mov ax, @data
	mov ds, ax
 	call SetVideoMode
; 	call SetScreenBackground
 	call Draw_Some_Pixels
 	call RestoreVideoMode
 	exit
main ENDP


Draw_Some_Pixels PROC
; Встановлює колір окремого елемента палітри кольорів і
; креслить на екрані кілька пікселів
; Змінимо колір елемента палітри, що визначається індексом 1,

 	mov dx, 3c8h		; Порт індексу палітри кольорів (3C8h)
 	mov al, 1			; Встановимо індекс 1
 	out dx, al
 	mov dx, 3c9h 		;Значення кольорів виводяться у порт 3C9h
 	mov al, 0			; Червоний колір
 	out dx, al
 	mov al, 45			; Зелений колір
 	out dx, al
	mov al, 0			; Синій колір
 	out dx, al



	;top horizontal
 	mov ax, 320		; Кількість пікселів у рядку
 	mul yVal			; множимо на координату Y,
 	add ax, xVAl		; і додаємо координату X.
 	mov cx, 91			; Виведемо 10 пікселів
 	mov di, ax			; в АХ – зміщення відеобуфера
	mov offset_, 1
	call drawLine

	;right vertical
	mov cx, 66
	mov offset_, 320
	call drawLine

	;bottom horizantal
	mov cx, 91
	mov ax, 320
	mov offset_, 66
 	mul offset_
	add ax, xVAl
	mov di, ax
	mov offset_, 1
	call drawLine

	;left vartical
	mov ax, 320		; Кількість пікселів у рядку
 	mul yVal			; множимо на координату Y,
 	add ax, xVAl		; і додаємо координату X.
	mov cx, 66
	mov offset_, 320
	mov di, ax
	call drawLine



	ret
Draw_Some_Pixels ENDP

drawLine PROC
DPI:
 	mov BYTE PTR es:[di], 1 ; Записуємо індекс кольору
			; За замовчуванням при зверненні до пам'яті через регістр DI процесор
			; використовує сегментний регістр DS. У нашому випадку ми використовували
			; команду заміни сегмента (es:[di]), щоб повідомити процесору про те, що при
			; зверненні до пам'яті замість регістра DS потрібно використовувати регістр ES.
			; Нагадаємо, що в регістрі ES зберігається сегментна адреса відеобуфера.
 	add di, offset_			; Зсунемось праворуч на 1 піксель
 	Loop DPI
 	ret
drawLine ENDP




;SetScreenBackground PROC
; Встановлює колір екрану фону.
; Як колір фону використовується нульовий елемент палітри кольорів.
; mov dx, 3c8h		; Порт вибору палітри кольорів (3C8h)
; mov al, 0			; Встановимо індекс палітри кольорів
; out dx, al
; Для керування палітрою кольорів використовуються два регістри виводу.
; Значення, записане в порт 3C8h, визначає номер елемента палітри кольорів,
; який планується змінити.
; Після записування індексу в порт 3C8h, в порт 3C9h записують значення
; кольорів.
; Встановимо темно-синій фон екрану
; 	mov dx, 3c9h		; Значення кольорів виводяться  в порт 3C9h
; 	mov al, 45			; Значення червоного кольору
; 	out dx, al
; 	mov al, 15			; Значення зеленого кольору
; 	out dx,al
;	mov al, 8			; Значення синього кольору
; 	out dx, al			; (інтенсивність 35/63)
;	ret
;SetScreenBackground ENDP


SetVideoMode PROC
; Зберігає значення поточного відеорежиму,
; перемикає відеоадаптер у режим 13h і завантажує
; в регістр ES сегментну адресу відеобуфера.
;---------------------------------------------------------
 	mov ah, 0Fh		; Визначимо поточний відеорежим
 	int 10h
 	mov saveMode, al	; Збережемо його
	mov ah, 0			; Встановимо новий відеорежим
 	mov al, 13h			; номер 13h
	int 10h
	push 0A000h		; Сегментна адреса відеобуфера
	pop es			; ES = A000h (відеосегмент)
	ret
SetVideoMode ENDP



;---------------------------------------------------------
RestoreVideoMode PROC
; Чекає натиснення будь-якої клавіші, а потім відновлює
; початковий відеорежим
 	mov ah, 10h 		; Чекаємо натиснення клавіші
 	int 16h
 	mov ah, 0		; Відновимо старий відеорежим
 	mov al, saveMode
 	int 10h
 	ret
RestoreVideoMode ENDP
END main