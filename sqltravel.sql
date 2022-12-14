USE [login]
GO
/****** Object:  Table [dbo].[Flight]    Script Date: 07/12/2022 7:29:00 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Flight](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[diemkhoihanh] [nchar](50) NULL,
	[diemden] [nchar](50) NULL,
	[ngaydi] [nchar](20) NULL,
	[sohanhkhach] [nchar](50) NULL,
	[hangghe] [nchar](30) NULL,
	[price] [int] NULL,
	[username] [char](30) NULL,
 CONSTRAINT [PK_Flight] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Hotel]    Script Date: 07/12/2022 7:29:00 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Hotel](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[img] [varchar](100) NULL,
	[name] [nchar](30) NULL,
	[gia] [nchar](30) NULL,
	[addr] [nchar](50) NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[hoteladd]    Script Date: 07/12/2022 7:29:00 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[hoteladd](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[namehotel] [nvarchar](50) NULL,
	[giahotel] [nchar](30) NULL,
	[addr] [nchar](50) NULL,
	[username] [text] NULL
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[logintravel]    Script Date: 07/12/2022 7:29:00 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[logintravel](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[email] [nchar](30) NULL,
	[username] [nchar](30) NULL,
	[password] [nchar](30) NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Users]    Script Date: 07/12/2022 7:29:00 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Users](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[username] [text] NOT NULL,
	[password] [text] NULL,
PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[Flight] ON 

INSERT [dbo].[Flight] ([id], [diemkhoihanh], [diemden], [ngaydi], [sohanhkhach], [hangghe], [price], [username]) VALUES (1, N'TP HCM                                            ', N'Ha Noi                                            ', N'2022-11-25          ', N'3                                                 ', N'Thuong Gia                    ', NULL, NULL)
INSERT [dbo].[Flight] ([id], [diemkhoihanh], [diemden], [ngaydi], [sohanhkhach], [hangghe], [price], [username]) VALUES (10, N'HCM                                               ', N'Ha Noi                                            ', N'26/11/2022          ', N'4                                                 ', N'Economy Class Seat            ', 2000000, NULL)
INSERT [dbo].[Flight] ([id], [diemkhoihanh], [diemden], [ngaydi], [sohanhkhach], [hangghe], [price], [username]) VALUES (11, N'HCM                                               ', N'Ha Noi                                            ', N'26/11/2022          ', N'4                                                 ', N'Economy Class Seat            ', 2000000, NULL)
INSERT [dbo].[Flight] ([id], [diemkhoihanh], [diemden], [ngaydi], [sohanhkhach], [hangghe], [price], [username]) VALUES (12, N'HCM                                               ', N'Ha Noi                                            ', N'26/11/2022          ', N'8                                                 ', N'Economy Class Seat            ', 2000000, NULL)
INSERT [dbo].[Flight] ([id], [diemkhoihanh], [diemden], [ngaydi], [sohanhkhach], [hangghe], [price], [username]) VALUES (13, N'HCM                                               ', N'Ha Noi                                            ', N'26/11/2022          ', N'8                                                 ', N'Economy Class Seat            ', 2000000, NULL)
INSERT [dbo].[Flight] ([id], [diemkhoihanh], [diemden], [ngaydi], [sohanhkhach], [hangghe], [price], [username]) VALUES (14, N'HCM                                               ', N'Ha Noi                                            ', N'26/11/2022          ', N'5                                                 ', N'Business Class Seats          ', 5000000, NULL)
INSERT [dbo].[Flight] ([id], [diemkhoihanh], [diemden], [ngaydi], [sohanhkhach], [hangghe], [price], [username]) VALUES (15, N'HCM                                               ', N'Ha Noi                                            ', N'26/11/2022          ', N'5                                                 ', N'Business Class Seats          ', 5000000, NULL)
INSERT [dbo].[Flight] ([id], [diemkhoihanh], [diemden], [ngaydi], [sohanhkhach], [hangghe], [price], [username]) VALUES (16, N'HCM                                               ', N'Ha Noi                                            ', N'26/11/2022          ', N'3                                                 ', N'Business Class Seats          ', 5000000, NULL)
INSERT [dbo].[Flight] ([id], [diemkhoihanh], [diemden], [ngaydi], [sohanhkhach], [hangghe], [price], [username]) VALUES (17, N'HCM                                               ', N'Ha Noi                                            ', N'26/11/2022          ', N'9                                                 ', N'Economy Class Seat            ', 2000000, NULL)
INSERT [dbo].[Flight] ([id], [diemkhoihanh], [diemden], [ngaydi], [sohanhkhach], [hangghe], [price], [username]) VALUES (18, N'HCM                                               ', N'Ha Noi                                            ', N'26/11/2022          ', N'7                                                 ', N'Business Class Seats          ', 5000000, N'a                             ')
INSERT [dbo].[Flight] ([id], [diemkhoihanh], [diemden], [ngaydi], [sohanhkhach], [hangghe], [price], [username]) VALUES (19, N'HCM                                               ', N'Quang Tri                                         ', N'26/11/2022          ', N'5                                                 ', N'Business Class Seats          ', 5000000, N'                              ')
INSERT [dbo].[Flight] ([id], [diemkhoihanh], [diemden], [ngaydi], [sohanhkhach], [hangghe], [price], [username]) VALUES (20, N'HCM                                               ', N'Ha Noi                                            ', N'26/11/2022          ', N'8                                                 ', N'Business Class Seats          ', 5000000, N'kun510                        ')
INSERT [dbo].[Flight] ([id], [diemkhoihanh], [diemden], [ngaydi], [sohanhkhach], [hangghe], [price], [username]) VALUES (21, N'HCM                                               ', N'Ha Noi                                            ', N'26/11/2022          ', N'1                                                 ', N'Business Class Seats          ', 5000000, N'kun510                        ')
INSERT [dbo].[Flight] ([id], [diemkhoihanh], [diemden], [ngaydi], [sohanhkhach], [hangghe], [price], [username]) VALUES (22, N'HCM                                               ', N'Ha Noi                                            ', N'30/12/2022          ', N'8                                                 ', N'Business Class Seats          ', 5000000, N'                              ')
INSERT [dbo].[Flight] ([id], [diemkhoihanh], [diemden], [ngaydi], [sohanhkhach], [hangghe], [price], [username]) VALUES (23, N'HCM                                               ', N'Ha Noi                                            ', N'10/12/2022          ', N'4                                                 ', N'Business Class Seats          ', 5000000, N'                              ')
SET IDENTITY_INSERT [dbo].[Flight] OFF
GO
SET IDENTITY_INSERT [dbo].[Hotel] ON 

INSERT [dbo].[Hotel] ([id], [img], [name], [gia], [addr]) VALUES (26, N'https://bom.so/f5fKlQ', N'Epalumpua                     ', N'2123111                       ', N'21 Tien Son 17                                    ')
INSERT [dbo].[Hotel] ([id], [img], [name], [gia], [addr]) VALUES (11, N'https://bom.so/f5fKlQ', N'Hyatt Regency Danang Resort   ', N'500000                        ', N'5 Trường Sa, Street, Ngũ Hành Sơn, Ðà Nẵng        ')
INSERT [dbo].[Hotel] ([id], [img], [name], [gia], [addr]) VALUES (12, N'https://bom.so/MNUpvv', N'Pullman Danang Beach Resort   ', N'200000                        ', N'101 Võ Nguyên Giáp, Ngũ Hành Sơn, Ðà Nẵng         ')
INSERT [dbo].[Hotel] ([id], [img], [name], [gia], [addr]) VALUES (13, N'https://bom.so/oDnjvz', N'Risemount Premier Resort      ', N'500200                        ', N'120 Nguyễn Văn Thởi Ngũ Hành Sơn, Ðà Nẵng         ')
INSERT [dbo].[Hotel] ([id], [img], [name], [gia], [addr]) VALUES (14, N'https://bom.so/f5fKlQ', N'Hyatt Regency Danang Resort   ', N'500000                        ', N'5 Trường Sa, Street, Ngũ Hành Sơn, Ðà Nẵng        ')
INSERT [dbo].[Hotel] ([id], [img], [name], [gia], [addr]) VALUES (15, N'https://bom.so/MNUpvv', N'Pullman Danang Beach Resort   ', N'200000                        ', N'101 Võ Nguyên Giáp, Ngũ Hành Sơn, Ðà Nẵng         ')
INSERT [dbo].[Hotel] ([id], [img], [name], [gia], [addr]) VALUES (16, N'https://bom.so/oDnjvz', N'Risemount Premier Resort      ', N'500200                        ', N'120 Nguyễn Văn Thởi Ngũ Hành Sơn, Ðà Nẵng         ')
INSERT [dbo].[Hotel] ([id], [img], [name], [gia], [addr]) VALUES (17, N'https://bom.so/FUfpRG', N'Khách sạn Saigon Morin        ', N'245000                        ', N'30 Lê Lợi, Phú Hỏi, Thành Phan Huy, Thừa Thiên Huế')
INSERT [dbo].[Hotel] ([id], [img], [name], [gia], [addr]) VALUES (18, N'https://bom.so/F74s0F', N'White Lotus Hue Hotel         ', N'278000                        ', N'05-07 Hoàng Hoa Thám, Phú Nhuởng, Thành phố Huế   ')
INSERT [dbo].[Hotel] ([id], [img], [name], [gia], [addr]) VALUES (19, N'https://bom.so/6RvXxh', N'Khách Sạn Mường Thanh Hué     ', N'245000                        ', N'38 Lê Lợii, Phú Hợi, Thành phố Huế,Huế            ')
INSERT [dbo].[Hotel] ([id], [img], [name], [gia], [addr]) VALUES (20, N'https://bom.so/FUfpRG', N'Khách sạn Saigon Morin        ', N'245000                        ', N'38 Lê Lợii, Phú Hợi, Thành phố Huế,Huế            ')
INSERT [dbo].[Hotel] ([id], [img], [name], [gia], [addr]) VALUES (21, N'https://bom.so/fg3Tee', N'cuongdeptraiqua               ', N'21041111                      ', N'quang tri                                         ')
INSERT [dbo].[Hotel] ([id], [img], [name], [gia], [addr]) VALUES (23, N'https://bom.so/PX22Cm', N'datHotel                      ', N'201001010                     ', N'da nang                                           ')
INSERT [dbo].[Hotel] ([id], [img], [name], [gia], [addr]) VALUES (24, N'asdasdasd', N'Cuongdeptraiqualamoi          ', N'123423423                     ', N'Ðà Nang                                           ')
SET IDENTITY_INSERT [dbo].[Hotel] OFF
GO
SET IDENTITY_INSERT [dbo].[hoteladd] ON 

INSERT [dbo].[hoteladd] ([id], [namehotel], [giahotel], [addr], [username]) VALUES (8, N'Risemount Premier Resort      ', N'500200                        ', N'120 Nguyễn Văn Thởi Ngũ Hành Sơn, Ðà Nẵng        ', N'')
INSERT [dbo].[hoteladd] ([id], [namehotel], [giahotel], [addr], [username]) VALUES (9, N'Khách sạn Saigon Morin        ', N'245000                        ', N'30 Lê Lợi, Phú Hỏi, Thành Phan Huy, Thừa Thiên Huế ', N'')
INSERT [dbo].[hoteladd] ([id], [namehotel], [giahotel], [addr], [username]) VALUES (10, N'Hyatt Regency Danang Resort   ', N'500000                        ', N'5 Trường Sa, Street, Ngũ Hành Sơn, Ðà Nẵng        ', N'')
SET IDENTITY_INSERT [dbo].[hoteladd] OFF
GO
SET IDENTITY_INSERT [dbo].[logintravel] ON 

INSERT [dbo].[logintravel] ([id], [email], [username], [password]) VALUES (51, N'cuongtran8c@gmail.com         ', N'kun510                        ', N'123456                        ')
INSERT [dbo].[logintravel] ([id], [email], [username], [password]) VALUES (52, N'cuong@yahoo.com               ', N'a                             ', N'123123                        ')
INSERT [dbo].[logintravel] ([id], [email], [username], [password]) VALUES (53, N'dovanlam@gmail.com            ', N'lam1207                       ', N'123456788                     ')
INSERT [dbo].[logintravel] ([id], [email], [username], [password]) VALUES (9, N'cuongtran1@gmail.com          ', N'cuong1                        ', N'1234567                       ')
INSERT [dbo].[logintravel] ([id], [email], [username], [password]) VALUES (44, N'cuong@yahoo.com               ', N'a                             ', N'123123                        ')
SET IDENTITY_INSERT [dbo].[logintravel] OFF
GO
